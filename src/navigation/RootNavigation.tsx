import { ROUTES } from "@/constants";
import { RootState } from "@/redux/store";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";
import { useSelector } from "react-redux";
import AppNavigation from "./AppNavigation";
import AuthNavigation from "./AuthNavigation";

const Stack = createNativeStackNavigator();

const RootNavigation = () => {
  const { accessToken, user } = useSelector((state: RootState) => state.auth);
  const isAuthed = !!accessToken && !!user;

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      {isAuthed ? (
        <Stack.Screen
          name={ROUTES.APP_NAVIAGATION_STACK}
          component={AppNavigation}
        />
      ) : (
        <Stack.Screen name={ROUTES.AUTHSTACK} component={AuthNavigation} />
      )}
    </Stack.Navigator>
  );
};

export default RootNavigation;
