import { ROUTES } from "@/constants";
import { Home } from "@/screens";
import AllEvents from "@/screens/tabs/Home/AllEvents";
import EventDetail from "@/screens/tabs/Home/EventDetail";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React from "react";

const HomeStack = () => {
  const Stack = createNativeStackNavigator();

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name={ROUTES.HOME} component={Home} />
      <Stack.Screen name={ROUTES.ALL_EVENTS} component={AllEvents} />
      <Stack.Screen name={ROUTES.EVENT_DETAIL} component={EventDetail} />
    </Stack.Navigator>
  );
};

export default HomeStack;
