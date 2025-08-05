import { useRestoreAuth } from "@/hooks/useRestoreAuth";
import RootNavigation from "@/navigation/RootNavigation";
import React from "react";
import { ActivityIndicator, View } from "react-native";

const RestoredApp = () => {
  const restored = useRestoreAuth();

  if (!restored) {
    return (
      <View className="flex-1 items-center justify-center">
        <ActivityIndicator size="large" color="#7690E4" />
      </View>
    );
  }

  return <RootNavigation />;
};

export default RestoredApp;
