import { ROUTES } from "@/constants";
import { RootState } from "@/redux/store";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import { useSelector } from "react-redux";
import AuthNavigation from "./AuthNavigation";
import OnBoardingNavigation from "./OnBoardingNavigation";

const Stack = createNativeStackNavigator();

const RootNavigation = () => {
  const { token, user } = useSelector((state: RootState) => state.auth);

  // Choose initial route based on auth state
  const initialRoute =
    token && user ? ROUTES.ONBOARDINGSTACK : ROUTES.AUTHSTACK;

  return (
    <Stack.Navigator
      initialRouteName={initialRoute}
      screenOptions={{ headerShown: false }}
    >
      <Stack.Screen name={ROUTES.AUTHSTACK} component={AuthNavigation} />
      <Stack.Screen
        name={ROUTES.ONBOARDINGSTACK}
        component={OnBoardingNavigation}
      />
    </Stack.Navigator>
  );
};

export default RootNavigation;
