import API from "@/config/api";

export type OnboardingPayload = {
  fullName: string;
  phoneNumber: string;
  location: string;
  linkedin: string;
  expertise: string; // comma-joined
  CompanyName: string;
  Designation: string;
  yearOfExperience: number;
};

export type OnboardingResponse = { message: string };

export async function createOnBoarding(payload: OnboardingPayload) {
  const { data } = await API.post<OnboardingResponse>(
    "/user/onBoarding",
    payload
  );
  return data;
}
