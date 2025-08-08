import { ROUTES } from "@/constants";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import OnBoardingNavigation from "./OnBoardingNavigation";
import TabNavigation from "./TabNavigation";

const Stack = createNativeStackNavigator();

const AppNavigation = () => {
  return (
    <Stack.Navigator
      initialRouteName={ROUTES.ONBOARDINGSTACK}
      screenOptions={{ headerShown: false }}
    >
      <Stack.Screen
        name={ROUTES.ONBOARDINGSTACK}
        component={OnBoardingNavigation}
      />

      <Stack.Screen name={ROUTES.TAB_NAVIAGATION} component={TabNavigation} />
    </Stack.Navigator>
  );
};

export default AppNavigation;
