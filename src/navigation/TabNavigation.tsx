import Events from "@/screens/tabs/Events";
import Home from "@/screens/tabs/Home";
import Profile from "@/screens/tabs/Profile";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { PlatformPressable } from "@react-navigation/elements";
import { House, PartyPopper, UserCircle } from "lucide-react-native";
import React from "react";

const Tab = createBottomTabNavigator();

const TabNavigation = () => {
  return (
    <Tab.Navigator
      initialRouteName={"home"}
      screenOptions={{
        headerShown: false,
        tabBarShowLabel: false,
        tabBarActiveTintColor: "#7690E4",
        tabBarInactiveTintColor: "#3A4155",
        tabBarButton: (props) => (
          <PlatformPressable
            {...props}
            android_ripple={{ color: "transparent" }} // Disables the ripple effect for Android
            pressColor="transparent" //For android
            pressOpacity={0.3} //For ios
          />
        ),
      }}
    >
      <Tab.Screen
        name={"home"}
        component={Home}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <House size={24} strokeWidth={2} color={color} />
          ),
        })}
      />

      <Tab.Screen
        name={"events"}
        component={Events}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <PartyPopper size={24} strokeWidth={2} color={color} />
          ),
        })}
      />

      <Tab.Screen
        name={"profile"}
        component={Profile}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <UserCircle size={24} strokeWidth={2} color={color} />
          ),
        })}
      />
    </Tab.Navigator>
  );
};

export default TabNavigation;
