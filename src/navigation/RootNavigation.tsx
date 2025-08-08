import { ROUTES } from "@/constants";
import { RootState } from "@/redux/store";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import { useSelector } from "react-redux";
import AppNavigation from "./AppNavigation";
import AuthNavigation from "./AuthNavigation";

const Stack = createNativeStackNavigator();

const RootNavigation = () => {
  const { token, user } = useSelector((state: RootState) => state.auth);

  // Choose initial route based on auth state
  const initialRoute =
    token && user ? ROUTES.APP_NAVIAGATION_STACK : ROUTES.AUTHSTACK;

  return (
    <Stack.Navigator
      initialRouteName={initialRoute}
      screenOptions={{ headerShown: false }}
    >
      <Stack.Screen name={ROUTES.AUTHSTACK} component={AuthNavigation} />
      <Stack.Screen
        name={ROUTES.APP_NAVIAGATION_STACK}
        component={AppNavigation}
      />
    </Stack.Navigator>
  );
};

export default RootNavigation;
