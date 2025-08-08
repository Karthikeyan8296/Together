import { ROUTES } from "@/constants";
import { LogIn, SignUp, Welcome } from "@/screens";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";

const Stack = createNativeStackNavigator();

const AuthNavigation = () => {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name={ROUTES.WELCOME} component={Welcome} />
      <Stack.Screen name={ROUTES.SIGNUP} component={SignUp} />
      <Stack.Screen name={ROUTES.LOGIN} component={LogIn} />
    </Stack.Navigator>
  );
};

export default AuthNavigation;
