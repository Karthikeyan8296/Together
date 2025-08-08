import Header from "@/components/Header";
import InputField from "@/components/InputField";
import MultiSelectTags from "@/components/MultiSelectTags";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { ChevronLeft } from "lucide-react-native";
import React, { useState } from "react";
import { View } from "react-native";

const OnBoarding2 = ({ navigation }: any) => {
  const [linkedin, setLinkedin] = useState("");
  const [selectedExpertise, setSelectedExpertise] = useState<string[]>([]);

  const ExpertiseOptions = [
    { label: "Mobile App Dev", value: "Mobile App Dev" },
    { label: "Web Dev", value: "Web Dev" },
    { label: "UI/UX", value: "UI/UX" },
    { label: "Game Dev", value: "Game Dev" },
    { label: "Data Sci", value: "Data Sci" },
    { label: "AI/ML", value: "AI/ML" },
    { label: "CyberSec", value: "CyberSec" },
    { label: "Cloud", value: "Cloud" },
    { label: "IoT", value: "IoT" },
    { label: "Blockchain", value: "Blockchain" },
    { label: "AR/VR", value: "AR/VR" },
    { label: "3D Art", value: "3D Art" },
    { label: "Testing", value: "Testing" },
    { label: "DevOps", value: "DevOps" },
    { label: "Embedded", value: "Embedded" },
    { label: "DB Admin", value: "DB Admin" },
    { label: "SEO", value: "SEO" },
    { label: "PM", value: "PM" },
    { label: "Video Edit", value: "Video Edit" },
    { label: "Content Write", value: "Content Write" },
    { label: "Marketing", value: "Marketing" },
  ];

  const isAllFilled = linkedin.trim() !== "" && selectedExpertise !== null;

  const handleContinue = () => {
    if (isAllFilled) {
      navigation.navigate(ROUTES.ONBOARDING_3);
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
          Your Professional Profile
        </Typo>
        <Typo font="Inter_medium" size={14}>
          Help us connect you with the right events and opportunities.
        </Typo>
      </View>

      {/* Content */}
      <View className="mx-8 mt-6">
        <View>
          <Typo className="mb-2" font="Inter_semiBold" size={16}>
            Linkedin Profile
          </Typo>
          <InputField
            value={linkedin}
            onChangeText={(value) => setLinkedin(value)}
            placeholder="https://linkedin.com/in/yourprofile"
            keyboardType="url"
            state={"default"}
            helperText={""}
          />
        </View>

        <View className="mt-6">
          <Typo className="mb-1" font="Inter_semiBold" size={16}>
            Area of Expertise
          </Typo>

          <MultiSelectTags
            options={ExpertiseOptions}
            selectedValues={selectedExpertise}
            onChange={setSelectedExpertise}
          />
        </View>

        <View className="mt-6">
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

export default OnBoarding2;
