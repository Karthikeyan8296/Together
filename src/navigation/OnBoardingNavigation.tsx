import { ROUTES } from "@/constants";
import { Onbaording1, OnbaordingWelcome } from "@/screens";
import OnBoarding2 from "@/screens/onBoarding/OnBoarding2";
import Onboarding3 from "@/screens/onBoarding/Onboarding3";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";

const Stack = createNativeStackNavigator();

const OnBoardingNavigation = () => {
  return (
    <Stack.Navigator
      // initialRouteName={ROUTES.ONBOARDING_3}
      screenOptions={{ headerShown: false }}
    >
      <Stack.Screen
        name={ROUTES.ONBOARDING_WELCOME}
        component={OnbaordingWelcome}
      />
      <Stack.Screen name={ROUTES.ONBOARDING_1} component={Onbaording1} />
      <Stack.Screen name={ROUTES.ONBOARDING_2} component={OnBoarding2} />
      <Stack.Screen name={ROUTES.ONBOARDING_3} component={Onboarding3} />
    </Stack.Navigator>
  );
};

export default OnBoardingNavigation;
