import { ROUTES } from "@/constants";
import { Onbaording1, OnbaordingWelcome } from "@/screens";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";

const Stack = createNativeStackNavigator();

const OnBoardingNavigation = () => {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen
        name={ROUTES.ONBOARDING_WELCOME}
        component={OnbaordingWelcome}
      />
      <Stack.Screen name={ROUTES.ONBOARDING_1} component={Onbaording1} />
    </Stack.Navigator>
  );
};

export default OnBoardingNavigation;
