import { ROUTES } from "@/constants";
import { RootState } from "@/redux/store";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import { useSelector } from "react-redux";
import OnBoardingNavigation from "./OnBoardingNavigation";
import TabNavigation from "./TabNavigation";

const Stack = createNativeStackNavigator();

const AppNavigation = () => {
  const { user } = useSelector((state: RootState) => state.auth);
  const isOnboarded = !!user?.isOnboarded;

  if (!isOnboarded) {
    return (
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen
          name={ROUTES.ONBOARDINGSTACK}
          component={OnBoardingNavigation}
        />
      </Stack.Navigator>
    );
  }

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name={ROUTES.TAB_NAVIAGATION} component={TabNavigation} />
    </Stack.Navigator>
  );
};

export default AppNavigation;
