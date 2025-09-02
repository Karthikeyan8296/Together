import { ChevronLeft } from "lucide-react-native";
import React from "react";
import { TouchableOpacity, View } from "react-native";
import Typo from "./Typo";

export const CenterHeader = ({
  isLeftIcon,
  onBackPress,
  title,
  isRightIcon,
  rightIcon,
  rightIconOnPress,
}: {
  onBackPress?: () => void;
  title: string;
  isLeftIcon?: boolean;
  isRightIcon?: boolean;
  rightIcon?: React.ReactNode;
  rightIconOnPress?: () => void;
}) => {
  return (
    <View className="w-full flex-row h-20  justify-center relative">
      {isLeftIcon && (
        <>
          <ChevronLeft
            onPress={onBackPress}
            style={{
              position: "absolute",
              left: 24,
              top: 32,
            }}
          />
        </>
      )}

      <Typo
        font="Inter_bold"
        size={16}
        className="text-center self-center mt-6"
      >
        {title}
      </Typo>

      {isRightIcon && (
        <TouchableOpacity
          onPress={rightIconOnPress}
          style={{
            padding: 8,
            borderRadius: 999,
            backgroundColor: "white",
            position: "absolute",
            right: 24,
            top: 24,
          }}
        >
          {rightIcon}
        </TouchableOpacity>
      )}
    </View>
  );
};
