import { AnimatedTabBar } from "@/components/AnimatedTabBar";
import { ROUTES } from "@/constants";
import { CreateEvent, Discover, Notification, Profile } from "@/screens";
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
import HomeStack from "./HomeStack";

const Tab = createBottomTabNavigator();

const baseTabBarStyle = {
  flexDirection: "row" as const,
  justifyContent: "space-evenly" as const,
  paddingHorizontal: 50,
  borderTopLeftRadius: 24,
  borderTopRightRadius: 24,
  height: 76,
  borderWidth: 1,
  elevation: 0,
  shadowColor: "transparent",
  shadowOffset: { width: 0, height: 0 },
  shadowOpacity: 0,
  shadowRadius: 0,
  paddingBottom: 20,
  paddingTop: 16,
  backgroundColor: "#fff",
};

const TabNavigation = () => {
  const hiddenRoutes = [ROUTES.ONBOARDINGSTACK];

  return (
    <Tab.Navigator
      initialRouteName={ROUTES.HOME_STACK}
      // Use animated tab bar instead of the default one
      tabBar={(props) => (
        <AnimatedTabBar {...props} hiddenRoutes={hiddenRoutes} />
      )}
      screenOptions={{
        headerShown: false,
        tabBarShowLabel: false,
        tabBarActiveTintColor: "#7690E4",
        tabBarInactiveTintColor: "#3A4155",
        tabBarButton: (p) => (
          <PlatformPressable
            {...p}
            android_ripple={{ color: "transparent" }}
            pressColor="transparent"
            pressOpacity={0.3}
          />
        ),
        tabBarStyle: baseTabBarStyle,
      }}
    >
      <Tab.Screen
        name={ROUTES.HOME_STACK}
        component={HomeStack}
        options={{
          tabBarIcon: ({ color }) => <House color={color} />,
          tabBarStyle: baseTabBarStyle,
        }}
      />
      <Tab.Screen
        name={"dicover"}
        component={Discover}
        options={{
          tabBarIcon: ({ color }) => <Compass color={color} />,
          tabBarStyle: baseTabBarStyle,
        }}
      />
      <Tab.Screen
        name={"event"}
        component={CreateEvent}
        options={{
          tabBarIcon: ({ color }) => <CirclePlus color={color} />,
          tabBarStyle: baseTabBarStyle,
        }}
      />
      <Tab.Screen
        name={"notification"}
        component={Notification}
        options={{
          tabBarIcon: ({ color }) => <Heart color={color} />,
          tabBarStyle: baseTabBarStyle,
        }}
      />
      <Tab.Screen
        name={"profile"}
        component={Profile}
        options={{
          tabBarIcon: ({ color }) => <UserCircle color={color} />,
          tabBarStyle: baseTabBarStyle,
        }}
      />
    </Tab.Navigator>
  );
};

export default TabNavigation;
