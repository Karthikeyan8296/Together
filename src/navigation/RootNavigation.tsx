import { ROUTES } from "@/constants";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import AuthNavigation from "./AuthNavigation";

const Stack = createNativeStackNavigator();

const RootNavigation = () => {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name={ROUTES.AUTHSTACK} component={AuthNavigation} />
    </Stack.Navigator>
  );
};

export default RootNavigation;
