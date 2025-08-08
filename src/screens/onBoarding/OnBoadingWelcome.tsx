import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { logout } from "@/redux/slices/authSlice";
import { RootState } from "@/redux/store";
import AsyncStorage from "@react-native-async-storage/async-storage";
import React from "react";
import { Button, View } from "react-native";
import { useDispatch, useSelector } from "react-redux";

const OnBoadingWelcome = ({ navigation }: any) => {
  const dispatch = useDispatch();
  const { email } = useSelector((state: RootState) => state.auth);

  console.log(email);

  const handleLogout = async () => {
    await AsyncStorage.removeItem("token");
    await AsyncStorage.removeItem("user");
    dispatch(logout());
    navigation.replace(ROUTES.AUTHSTACK);
  };
  return (
    <ScreenWrapper>
      <View className="flex-1 items-center justify-center">
        <Typo>{email}</Typo>
        <Button title="Logout" onPress={handleLogout} />
      </View>
    </ScreenWrapper>
  );
};

export default OnBoadingWelcome;
