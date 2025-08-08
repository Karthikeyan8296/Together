import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import React from "react";
import { Button, View } from "react-native";

const OnBoadingWelcome = ({ navigation }: any) => {
  return (
    <ScreenWrapper>
      <View className="flex-1 gap-5 items-center justify-center">
        <Typo>OnBoading Welcome</Typo>
        <Button
          title="Next"
          onPress={() => {
            navigation.navigate(ROUTES.ONBOARDING_1);
          }}
        />
      </View>
    </ScreenWrapper>
  );
};

export default OnBoadingWelcome;
