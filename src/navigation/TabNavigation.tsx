import { CreateEvent, Discover, Home, Notification, Profile } from "@/screens";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { PlatformPressable } from "@react-navigation/elements";
import {
  CirclePlus,
  Compass,
  Heart,
  House,
  UserCircle,
} from "lucide-react-native";
import React from "react";

const Tab = createBottomTabNavigator();

const TabNavigation = () => {
  return (
    <Tab.Navigator
      initialRouteName={"Home"}
      screenOptions={{
        headerShown: false,
        tabBarShowLabel: false,
        tabBarStyle: {
          flexDirection: "row",
          justifyContent: "space-evenly",
          paddingHorizontal: 50, //spacing btw the tabs
          borderTopLeftRadius: 24,
          borderTopRightRadius: 24,
          height: 76,
          borderTopWidth: 0.5,
          elevation: 0,
          shadowColor: "transparent",
          shadowOffset: { width: 0, height: 0 },
          shadowOpacity: 0,
          shadowRadius: 0,
          paddingBottom: 20,
          paddingTop: 16,
        },
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
        name={"Home"}
        component={Home}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <House size={24} strokeWidth={2} color={color} />
          ),
        })}
      />

      <Tab.Screen
        name={"Discover"}
        component={Discover}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <Compass size={24} strokeWidth={2} color={color} />
          ),
        })}
      />

      <Tab.Screen
        name={"CreateEvent"}
        component={CreateEvent}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <CirclePlus size={24} strokeWidth={2} color={color} />
          ),
        })}
      />

      <Tab.Screen
        name={"Notification"}
        component={Notification}
        // @ts-ignore
        options={({ route }) => ({
          tabBarIcon: ({ color }) => (
            <Heart size={24} strokeWidth={2} color={color} />
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
