import Header from "@/components/Header";
import InputField from "@/components/InputField";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { markOnboarded, persistAuth } from "@/redux/slices/authSlice";
import {
  clearOnboarding,
  selectOnboarding,
  setWorkExperience,
  submitOnboarding,
} from "@/redux/slices/onboardingSlice";
import { AppDispatch } from "@/redux/store";
import { ChevronLeft } from "lucide-react-native";
import React, { useState } from "react";
import { View } from "react-native";
import Toast from "react-native-toast-message";
import { useDispatch, useSelector } from "react-redux";

const Onboarding3 = ({ navigation }: any) => {
  const dispatch = useDispatch<AppDispatch>();
  const data = useSelector(selectOnboarding);

  const [companyName, setCompanyName] = useState(data.companyName);
  const [designation, setDesignation] = useState(data.designation);
  const [yearOfExperience, setYearOfExperience] = useState(
    data.yearOfExperience?.toString() ?? ""
  );

  const isAllFilled =
    companyName.trim() !== "" &&
    designation.trim() !== "" &&
    yearOfExperience.trim() !== "";

  const handleContinue = async () => {
    if (!isAllFilled) return;
    const calculatedExp = Math.min(
      99,
      Math.max(0, Number(yearOfExperience) || 0)
    );
    dispatch(
      setWorkExperience({
        companyName,
        designation,
        yearOfExperience: calculatedExp,
      })
    );

    try {
      await dispatch(submitOnboarding()).unwrap();
      //marking the onboarding as done!
      dispatch(markOnboarded());
      //persist the updated user
      await dispatch(persistAuth()).unwrap();
      //reset
      dispatch(clearOnboarding());
      Toast.show({ type: "success", text1: "Onboarding complete" });
      // navigation.reset({
      //   index: 0,
      //   routes: [{ name: ROUTES.TAB_NAVIAGATION }],
      // });
    } catch (err: any) {
      Toast.show({
        topOffset: 52,
        type: "error",
        text1: "Failed to onboard",
        text2: err?.toString?.() ?? "",
      });
    }
  };
  return (
    <ScreenWrapper>
      <Header
        rightIcon={
          <ChevronLeft
            onPress={() => {
              navigation.goBack();
            }}
          />
        }
      />
      <View className="mx-8">
        <Typo font="Inter_extraBold" size={20}>
          Your Work Experience
        </Typo>
        <Typo font="Inter_medium" size={14}>
          Highlight your role so the right opportunities find you at the event.
        </Typo>
      </View>

      <View className="mx-8">
        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Company name
          </Typo>
          <InputField
            value={companyName}
            onChangeText={(value) => setCompanyName(value)}
            placeholder="e.g., Infosys, Google"
            state={"default"}
            helperText={""}
          />
        </View>

        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Designation
          </Typo>
          <InputField
            value={designation}
            onChangeText={(value) => setDesignation(value)}
            placeholder="e.g., Software Engineer, Product Manager"
            state={"default"}
            helperText={""}
          />
        </View>

        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Years of Experience
          </Typo>
          <InputField
            value={yearOfExperience}
            onChangeText={(value) => setYearOfExperience(value)}
            placeholder="e.g., 5"
            state={"default"}
            keyboardType="number-pad"
            maxLength={2}
            helperText={""}
          />
        </View>

        <View className="mt-8">
          <PrimaryButton
            onPress={handleContinue}
            style={{ opacity: isAllFilled ? 1 : 0.7 }}
            width={94}
          >
            Continue
          </PrimaryButton>
        </View>
      </View>
    </ScreenWrapper>
  );
};

export default Onboarding3;
