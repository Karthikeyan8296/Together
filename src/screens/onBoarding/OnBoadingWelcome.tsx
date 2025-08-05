import ScreenWrapper from "@/components/ScreenWrapper";
import { ROUTES } from "@/constants";
import { logout } from "@/redux/slices/authSlice";
import AsyncStorage from "@react-native-async-storage/async-storage";
import React from "react";
import { Button, View } from "react-native";
import { useDispatch } from "react-redux";

const OnBoadingWelcome = ({ navigation }: any) => {
  const dispatch = useDispatch();

  const handleLogout = async () => {
    await AsyncStorage.removeItem("token");
    await AsyncStorage.removeItem("user");
    dispatch(logout());
    navigation.replace(ROUTES.AUTHSTACK);
  };
  return (
    <ScreenWrapper>
      <View className="flex-1 items-center justify-center">
        <Button title="Logout" onPress={handleLogout} />
      </View>
    </ScreenWrapper>
  );
};

export default OnBoadingWelcome;
