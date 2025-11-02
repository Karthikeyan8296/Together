import { CenterHeader } from "@/components/CenterHeader";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { useFetchData } from "@/hooks/useFetchData";
import { getYourEvents, YourEventsResponse } from "@/service/eventService";
import { ChevronRight, Settings } from "lucide-react-native";
import React from "react";
import {
  ActivityIndicator,
  FlatList,
  Image,
  TouchableOpacity,
  View,
} from "react-native";

const Home = ({ navigation }: any) => {
  /**
  $ Run this effect only once when the component first mounts - []
  $ if I remove this [] it will keep calling the api again and again
  $ if [userId], Run when userId changes, Fetch user-specific data
 */

  const { data, loading, error } = useFetchData<YourEventsResponse>(
    getYourEvents,
    []
  );

  const events = [
    ...(data?.hostedEvents.map((e) => ({ ...e, role: "Hosting" })) || []),
    ...(data?.registeredEvents.map((e) => ({ ...e, role: "Going" })) || []),
  ];

  return (
    <ScreenWrapper>
      <CenterHeader
        title="Together"
        isLeftIcon={false}
        isRightIcon={true}
        rightIcon={<Settings />}
        rightIconOnPress={() => {
          console.log("ispress");
        }}
      />

      <View className="flex-1 mx-8">
        <View className="flex-row justify-between items-center py-6">
          <Typo font="Inter_semiBold" size={20}>
            Your events
          </Typo>
          <TouchableOpacity
            onPress={() => navigation.navigate(ROUTES.ALL_EVENTS)}
            className="flex-row items-center justify-center"
          >
            <Typo size={14}>View All</Typo>
            <ChevronRight />
          </TouchableOpacity>
        </View>

        {loading && <ActivityIndicator />}
        {error && (
          <Typo size={14} className="text-red-500">
            {error}
          </Typo>
        )}

        <FlatList
          data={events}
          keyExtractor={(item) => item._id}
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
                  <View
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
                  </View>
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

export default Home;
