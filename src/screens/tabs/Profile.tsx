import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { logout } from "@/redux/slices/authSlice";
import { RootState } from "@/redux/store";
import AsyncStorage from "@react-native-async-storage/async-storage";
import React from "react";
import { Button, Text } from "react-native";
import { useDispatch, useSelector } from "react-redux";

const Profile = ({ navigation }: any) => {
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
    <ScreenWrapper className="flex-1 items-center justify-center">
      <Text>Profile</Text>
      <Typo>{email}</Typo>
      <Button title="Logout" onPress={handleLogout} />
    </ScreenWrapper>
  );
};

export default Profile;
