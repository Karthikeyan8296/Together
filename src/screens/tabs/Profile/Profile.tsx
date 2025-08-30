import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { logoutUser } from "@/redux/slices/authSlice";
import { AppDispatch, RootState } from "@/redux/store";
import { loadSession } from "@/util/tokenService";
import React from "react";
import { Button, Text } from "react-native";
import { useDispatch, useSelector } from "react-redux";

const Profile = ({ navigation }: any) => {
  const dispatch = useDispatch<AppDispatch>();

  const { email } = useSelector((state: RootState) => state.auth);

  console.log(email);
  const handleLogout = async () => {
    try {
      await dispatch(logoutUser());
      const session = await loadSession();
      console.log("Storage after logout:", session);
    } finally {
      navigation.replace(ROUTES.AUTHSTACK);
    }
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
