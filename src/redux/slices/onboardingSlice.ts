import API from "@/config/api";
import type { RootState } from "@/redux/store";
import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface OnboardingState {
  fullName: string;
  phoneNumber: string; // keep digits only
  location: string | null;
  linkedin: string;
  expertise: string[]; // MultiSelect from UI
  companyName: string;
  designation: string;
  yearOfExperience: number | null;
  loading: boolean;
  error: string | null;
}

const initialState: OnboardingState = {
  fullName: "",
  phoneNumber: "",
  location: null,
  linkedin: "",
  expertise: [],
  companyName: "",
  designation: "",
  yearOfExperience: null,
  loading: false,
  error: null,
};

export const submitOnboarding = createAsyncThunk<
  // return type
  { message: string } | void,
  // arg type
  void,
  // thunkAPI config
  { state: RootState; rejectValue: string }
>("onboarding/submit", async (_void, thunkAPI) => {
  const state = thunkAPI.getState().onboarding;

  const payload = {
    fullName: state.fullName,
    phoneNumber: state.phoneNumber,
    location: state.location ?? "",
    linkedin: state.linkedin,
    // Your UI collects an array; backend sample shows string.
    // Join into a single string (e.g., "Web Dev, AI/ML")
    expertise: state.expertise.join(", "),
    CompanyName: state.companyName,
    Designation: state.designation,
    yearOfExperience: Number(state.yearOfExperience ?? 0),
  };

  try {
    const res = await API.post("user/onBoarding", payload);
    return res.data;
  } catch (err: any) {
    return thunkAPI.rejectWithValue(
      err?.response?.data?.message || "Failed to complete onboarding"
    );
  }
});

const onboardingSlice = createSlice({
  name: "onboarding",
  initialState,
  reducers: {
    //basic info
    setBasicInfo: (
      state,
      action: PayloadAction<{
        fullName: string;
        phoneNumber: string;
        location: string;
      }>
    ) => {
      state.fullName = action.payload.fullName.trim();
      state.phoneNumber = action.payload.phoneNumber.replace(/\D/g, "");
      state.location = action.payload.location;
    },
    //Professional profile
    setProfessionalProfile: (
      state,
      action: PayloadAction<{ linkedin: string; expertise: string[] }>
    ) => {
      state.linkedin = action.payload.linkedin.trim();
      state.expertise = action.payload.expertise;
    },
    //Work Experience
    setWorkExperience: (
      state,
      action: PayloadAction<{
        companyName: string;
        designation: string;
        yearOfExperience: number;
      }>
    ) => {
      state.companyName = action.payload.companyName.trim();
      state.designation = action.payload.designation.trim();
      state.yearOfExperience = action.payload.yearOfExperience;
    },
    clearOnboarding: (state) => {
      Object.assign(state, initialState);
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(submitOnboarding.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(submitOnboarding.fulfilled, (state) => {
        state.loading = false;
      })
      .addCase(submitOnboarding.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? "Onboarding failed";
      });
  },
});

export const {
  setBasicInfo,
  setProfessionalProfile,
  setWorkExperience,
  clearOnboarding,
} = onboardingSlice.actions;

export const selectOnboarding = (state: RootState) => state.onboarding;
export default onboardingSlice.reducer;
