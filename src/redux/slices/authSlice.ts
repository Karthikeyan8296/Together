import API from "@/config/api";
import type { RootState } from "@/redux/store";
import { clearSession, loadSession, saveSession } from "@/util/tokenService";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

interface AuthState {
  email: string | null;
  signupToken: string | null;
  accessToken: string | null;
  refreshToken: string | null;
  user: any | null;
  loading: boolean;
  error: string | null;
}

const initialState: AuthState = {
  email: null,
  signupToken: null,
  accessToken: null,
  refreshToken: null,
  user: null,
  loading: false,
  error: null,
};

//send OTP
export const sendOtp = createAsyncThunk<
  string,
  string,
  { rejectValue: string }
>("auth/sendOtp", async (email, thunkAPI) => {
  try {
    const res = await API.post("auth/send-otp", { email });
    console.log("sendOtp success:", res.data);
    return email;
  } catch (err: any) {
    console.log("sendOtp error:", err.response?.data);
    return thunkAPI.rejectWithValue(
      err.response?.data?.message || "OTP failed"
    );
  }
});

//verify email
export const verifyEmail = createAsyncThunk<
  string, //return signupToken
  { email: string; otp: string }, //input type
  { rejectValue: string }
>("auth/verifyEmail", async ({ email, otp }, thunkAPI) => {
  try {
    const res = await API.post("auth/verify-email", { email, otp });
    console.log("verifyEmail success:", res.data);
    return res.data.signupToken;
  } catch (err: any) {
    console.log("verifyEmail error:", err.response?.data);
    return thunkAPI.rejectWithValue(
      err.response?.data?.message || "Invalid OTP"
    );
  }
});

//signup
export const signUpUser = createAsyncThunk<
  { user: any; accessToken: string; refreshToken: string }, //return type
  { signupToken: string; password: string }, //input type
  { rejectValue: string }
>("auth/signUp", async ({ signupToken, password }, thunkAPI) => {
  try {
    const res = await API.post("auth/sign-up", {
      signupToken,
      password,
    });
    console.log("signUpUser success:", res.data);
    return res.data;
  } catch (err: any) {
    console.log("signUpUser error:", err.response?.data);
    return thunkAPI.rejectWithValue(
      err.response?.data?.error || "Signup failed"
    );
  }
});

//login
export const loginUser = createAsyncThunk<
  { user: any; accessToken: string; refreshToken: string }, //return type
  { email: string; password: string }, // input type
  { rejectValue: string }
>("auth/login", async ({ email, password }, thunkAPI) => {
  try {
    const res = await API.post("auth/login", { email, password });
    console.log("loginUser success:", res.data);
    return res.data;
  } catch (err: any) {
    console.log("loginUser error:", err.response?.data);
    return thunkAPI.rejectWithValue(err.response?.data.error || "Login failed");
  }
});

//logout
export const logoutUser = createAsyncThunk<void, void>(
  "auth/logoutUser",
  async () => {
    try {
      const { refreshToken } = await loadSession();
      if (refreshToken) {
        await API.post("auth/logout", { refreshToken });
      }
    } catch {
      // ignore
    } finally {
      await clearSession();
    }
  }
);

//persist the updated user
export const persistAuth = createAsyncThunk<void, void, { state: RootState }>(
  "auth/persistAuth",
  async (_, { getState }) => {
    const { auth } = getState();
    if (auth.user && auth.accessToken && auth.refreshToken) {
      await saveSession(auth.user, auth.accessToken, auth.refreshToken);
    }
  }
);

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setAuthFromStorage: (state, action) => {
      const { user, accessToken, refreshToken } = action.payload;
      state.user = user;
      state.email = user?.email ?? null;
      state.accessToken = accessToken;
      state.refreshToken = refreshToken;
    },
    markOnboarded: (state) => {
      if (state.user) state.user.isOnboarded = true;
    },
  },
  extraReducers: (builder) => {
    //send OTP
    builder
      .addCase(sendOtp.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(sendOtp.fulfilled, (state, action) => {
        state.loading = false;
        state.email = action.payload;
      })
      .addCase(sendOtp.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Failed to send OTP";
      });

    //Verify Email
    builder
      .addCase(verifyEmail.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(verifyEmail.fulfilled, (state, action) => {
        state.loading = false;
        state.signupToken = action.payload;
      })
      .addCase(verifyEmail.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Failed to verify email";
      });
    //Signup
    builder
      .addCase(signUpUser.fulfilled, (state, action) => {
        const { user, accessToken, refreshToken } = action.payload;
        // signup usually doesn't return isOnboarded; default to false
        const mergedUser = {
          ...user,
          isOnboarded: Boolean(user?.isOnboarded ?? false),
        };
        state.user = mergedUser;
        state.accessToken = accessToken;
        state.refreshToken = refreshToken;
        state.email = user.email; //store the email in redux
        state.loading = false;

        //Persist signup and user
        saveSession(mergedUser, accessToken, refreshToken);
      })
      .addCase(signUpUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Signup failed";
      });
    //login
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        const { user, accessToken, refreshToken, isOnboarded } =
          action.payload as any;
        const mergedUser = {
          ...user,
          isOnboarded: Boolean(isOnboarded ?? user?.isOnboarded),
        };
        state.user = mergedUser;
        state.accessToken = accessToken;
        state.refreshToken = refreshToken;
        state.email = user.email; //store the email in redux
        state.loading = false;

        //Persist login and user
        saveSession(mergedUser, accessToken, refreshToken);
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Login failed";
      });

    //Logout
    builder.addCase(logoutUser.fulfilled, (state) => {
      state.user = null;
      state.email = null;
      state.accessToken = null;
      state.refreshToken = null;
      state.signupToken = null;
      state.loading = false;
      state.error = null;
    });
  },
});

export const { setAuthFromStorage, markOnboarded } = authSlice.actions;
export default authSlice.reducer;
