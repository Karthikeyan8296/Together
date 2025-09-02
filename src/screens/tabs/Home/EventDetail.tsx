import ScreenWrapper from "@/components/ScreenWrapper";
import React from "react";
import { Text } from "react-native";

const EventDetail = ({ navigation, route }: any) => {
  const eventId = route?.params.id;
  return (
    <ScreenWrapper className="flex items-center justify-center">
      <Text>EventDetail</Text>
      <Text>ID: {eventId}</Text>
    </ScreenWrapper>
  );
};

export default EventDetail;
