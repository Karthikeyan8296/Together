import React from "react";
import { Text, View } from "react-native";
import { ScreenWrapperProps } from "types";

const ScreenWrapper = ({
  style,
  backgroundColor,
  className,
  children,
}: ScreenWrapperProps) => {
  return (
    <View>
      <Text>ScreenWrapper</Text>
    </View>
  );
};

export default ScreenWrapper;
