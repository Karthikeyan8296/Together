import { colors } from "@/constants/colors";
import React from "react";
import { Dimensions, Platform, SafeAreaView, StatusBar } from "react-native";
import { ScreenWrapperProps } from "types";

const { height } = Dimensions.get("window");

const ScreenWrapper = ({
  style,
  backgroundColor,
  className,
  children,
}: ScreenWrapperProps) => {
  let paddingTop = Platform.OS === "ios" ? height * 0.06 : 40;

  return (
    <SafeAreaView
      className={className}
      style={[
        {
          paddingTop,
          flex: 1,
          backgroundColor: backgroundColor || colors.background,
        },
        style,
      ]}
    >
      <StatusBar barStyle={"dark-content"} />
      {children}
    </SafeAreaView>
  );
};

export default ScreenWrapper;
