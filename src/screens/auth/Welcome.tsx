import PrimaryButton from "@/components/PrimaryButton";
import Typo from "@/components/Typo";
import { IMAGES, ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import React from "react";
import { Image, ImageBackground, TouchableOpacity, View } from "react-native";

const Welcome = ({ navigation }: any) => {
  return (
    <ImageBackground source={IMAGES.Welcome_bg} className="flex-1">
      <View className="flex-1 items-center justify-between">
        <View className="flex-1"></View>
        <View className="flex-1"></View>
        <View className="flex-1"></View>
        {/* Logo and brandings */}
        <View className="items-center">
          <Image
            source={IMAGES.BrandLogo}
            style={{ width: 280, height: 80 }}
            resizeMode="contain"
          />
          <Typo
            font="Inter_medium"
            size={16}
            color={colors.white}
            className="pt-[16px]"
          >
            Connect. Celebrate. Repeat Together
          </Typo>
        </View>

        {/* Auth Buttons */}
        <View className="flex gap-y-4 mt-8 items-center">
          <PrimaryButton
            width={268}
            onPress={() => navigation.navigate(ROUTES.SIGNUP)}
          >
            Sign up
          </PrimaryButton>
          <PrimaryButton
            width={268}
            bg_color={colors.background}
            text_color={colors.black}
            onPress={() => navigation.navigate(ROUTES.LOGIN)}
          >
            Log in
          </PrimaryButton>
        </View>
        <TouchableOpacity onPress={() => {}}>
          <Typo
            color={colors.white}
            font="Inter_medium"
            size={14}
            className="mt-6"
          >
            About Together
          </Typo>
        </TouchableOpacity>

        <View className="flex-1"></View>
      </View>
    </ImageBackground>
  );
};

export default Welcome;
