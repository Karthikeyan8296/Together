import { ROUTES } from "@/constants";
import { Welcome } from "@/screens";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";

const Stack = createNativeStackNavigator();

const RootNavigation = () => {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name={ROUTES.WELCOME} component={Welcome} />
    </Stack.Navigator>
  );
};

export default RootNavigation;
