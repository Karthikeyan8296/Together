import API from "@/config/api";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

interface AuthState {
  email: string | null;
  signupToken: string | null;
  token: string | null;
  user: any | null;
  loading: boolean;
  error: string | null;
}

const initialState: AuthState = {
  email: null,
  signupToken: null,
  token: null,
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
  { user: any; token: string }, //return type
  { signupToken: string; password: string }, //input type
  { rejectValue: string }
>("auth/signUp", async ({ signupToken, password }, thunkAPI) => {
  try {
    const res = await API.post("auth/sign-up", {
      signupToken,
      password,
    });
    console.log("signUpUser success:", res.data);
    return { user: res.data.user, token: res.data.token };
  } catch (err: any) {
    console.log("signUpUser error:", err.response?.data);
    return thunkAPI.rejectWithValue(
      err.response?.data?.error || "Signup failed"
    );
  }
});

//login
export const loginUser = createAsyncThunk<
  { user: any; token: string }, //return type
  { email: string; password: string }, // input type
  { rejectValue: string }
>("auth/login", async ({ email, password }, thunkAPI) => {
  try {
    const res = await API.post("auth/login", { email, password });
    console.log("loginUser success:", res.data);
    return { user: res.data.user, token: res.data.token };
  } catch (err: any) {
    console.log("loginUser error:", err.response?.data);
    return thunkAPI.rejectWithValue(err.response?.data.error || "Login failed");
  }
});

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout: (state) => {
      state.user = null;
      state.token = null;
      state.email = null;
      state.signupToken = null;
    },
    setAuthFromStorage: (state, action) => {
      state.token = action.payload.token;
      state.user = action.payload.user;
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
        state.user = action.payload.user; //store the user in redux
        state.token = action.payload.token;
        state.email = action.payload.user.email; //store the email in redux
        state.loading = false;

        //Saving the token and user data to AsyncStorage after login/signup
        //Persist token and user
        AsyncStorage.setItem("token", action.payload.token);
        AsyncStorage.setItem("user", JSON.stringify(action.payload.user));
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
        state.user = action.payload.user;
        state.token = action.payload.token;
        state.email = action.payload.user.email; //store the email in redux
        state.loading = false;

        //Persist token and user
        AsyncStorage.setItem("token", action.payload.token);
        AsyncStorage.setItem("user", JSON.stringify(action.payload.user));
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Login failed";
      });
  },
});

export const { logout, setAuthFromStorage } = authSlice.actions;
export default authSlice.reducer;
