import Header from "@/components/Header";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import { getHostedEvents, getRegisteredEvents } from "@/service/eventService";
import { useQuery } from "@tanstack/react-query";
import { ChevronLeft } from "lucide-react-native";
import React, { useMemo, useState } from "react";
import {
  ActivityIndicator,
  FlatList,
  Image,
  TouchableOpacity,
  View,
} from "react-native";
import SegmentedControlTab from "react-native-segmented-control-tab";

const AllEvents = ({ navigation }: any) => {
  const [activeIndex, setActiveIndex] = useState(0);
  const isGoing = activeIndex === 0;

  const { data, isLoading, isFetching, error, refetch } = useQuery({
    queryKey: ["events", isGoing ? "registered" : "hosted"],
    queryFn: () => (isGoing ? getRegisteredEvents() : getHostedEvents()),
    placeholderData: (prev) => prev, //keep the previous data
  });

  const events = useMemo(
    () =>
      (data ?? []).map((e) => ({
        ...e,
        role: isGoing ? ("Going" as const) : ("Hosting" as const),
      })),
    [data, isGoing]
  );

  return (
    <ScreenWrapper>
      <Header
        rightIcon={
          <ChevronLeft
            onPress={() => {
              navigation.goBack();
            }}
          />
        }
      />

      <View className="flex-1 mx-8">
        {/* Segmented View */}
        <SegmentedControlTab
          values={["Going", "Hosting"]}
          activeTabStyle={{ backgroundColor: colors.primary }}
          tabStyle={{ borderColor: colors.primary }}
          selectedIndex={activeIndex}
          onTabPress={setActiveIndex}
        />

        {isLoading && <ActivityIndicator />}
        {error && (
          <Typo size={14} className="text-red-500">
            {error instanceof Error ? error.message : "Something went wrong"}
          </Typo>
        )}

        <FlatList
          data={events}
          keyExtractor={(item) => item._id}
          refreshing={isFetching}
          onRefresh={refetch}
          className="mt-6"
          renderItem={({ item }) => (
            <TouchableOpacity
              className="flex-row mb-4 bg-white rounded-2xl p-3 shadow"
              onPress={() =>
                navigation.navigate(ROUTES.EVENT_DETAIL, { id: item._id })
              }
            >
              <Image
                source={{ uri: item.coverImage }}
                className="w-20 h-20 rounded-xl mr-4"
              />
              <View className="flex-1">
                <View className="flex-row justify-between items-center">
                  <Typo font="Inter_semiBold" size={16}>
                    {item.eventName}
                  </Typo>
                  {/* <View
                    className={`px-2 py-1 rounded-md ${
                      item.role === "Hosting" ? "bg-blue-100" : "bg-green-100"
                    }`}
                  >
                    <Typo
                      size={10}
                      className={`${
                        item.role === "Hosting"
                          ? "text-blue-600"
                          : "text-green-600"
                      }`}
                    >
                      {item.role}
                    </Typo>
                  </View> */}
                </View>

                <Typo size={13} className="text-gray-500">
                  {item.location?.address}
                </Typo>
                <Typo size={12} className="text-gray-400">
                  {new Date(item.startTime).toLocaleDateString(undefined, {
                    weekday: "short",
                    month: "short",
                    day: "numeric",
                  })}
                </Typo>
              </View>
            </TouchableOpacity>
          )}
        />
      </View>
    </ScreenWrapper>
  );
};

export default AllEvents;
