import Header from "@/components/Header";
import InputField from "@/components/InputField";
import LocationPicker from "@/components/LocationPicker";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { selectOnboarding, setBasicInfo } from "@/redux/slices/onboardingSlice";
import { AppDispatch } from "@/redux/store";
import { ChevronLeft } from "lucide-react-native";
import React, { useState } from "react";
import { View } from "react-native";
import { useDispatch, useSelector } from "react-redux";

const OnBoarding1 = ({ navigation }: any) => {
  const dispatch = useDispatch<AppDispatch>();
  const data = useSelector(selectOnboarding);

  const [fullName, setFullName] = useState(data.fullName);
  const [phoneNumber, setPhoneNumber] = useState(data.phoneNumber);
  const [selectedLocation, setSelectedLocation] = useState<string | null>(
    data.location
  );

  const isAllFilled =
    fullName.trim() !== "" &&
    phoneNumber.replace(/\D/g, "").length === 10 &&
    selectedLocation !== null;

  const handleContinue = () => {
    if (isAllFilled) {
      dispatch(
        setBasicInfo({
          fullName,
          phoneNumber,
          location: selectedLocation as string,
        })
      );
      navigation.navigate(ROUTES.ONBOARDING_2);
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
          Tell Us About You
        </Typo>
        <Typo font="Inter_medium" size={14}>
          Share your basic details so we can personalize your experience.
        </Typo>
      </View>

      {/* Content */}
      <View className="mx-8 mt-6">
        <View>
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Full Name
          </Typo>
          <InputField
            value={fullName}
            onChangeText={(value) => setFullName(value)}
            placeholder="Enter your full name"
            state={"default"}
            helperText={""}
          />
        </View>

        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Phone Number
          </Typo>
          <InputField
            value={phoneNumber}
            onChangeText={(value) => setPhoneNumber(value)}
            placeholder="+91  XXXXX-XXXXX"
            keyboardType="phone-pad"
            maxLength={10}
            state={"default"}
            helperText={""}
          />
        </View>

        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Location
          </Typo>
          <LocationPicker
            selected={selectedLocation}
            setSelected={setSelectedLocation}
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

export default OnBoarding1;
