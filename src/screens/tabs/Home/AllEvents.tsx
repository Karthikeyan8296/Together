import Header from "@/components/Header";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import API from "@/config/api";
import { ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import SegmentedControl from "@react-native-segmented-control/segmented-control";
import { ChevronLeft } from "lucide-react-native";
import React, { useEffect, useState } from "react";
import {
  ActivityIndicator,
  FlatList,
  Image,
  TouchableOpacity,
  View,
} from "react-native";

const AllEvents = ({ navigation }: any) => {
  const [activeIndex, setActiveIndex] = useState(0);
  const [loading, setLoading] = useState(false);
  const [events, setEvents] = useState<any[]>([]);
  const [error, setError] = useState("");

  const fetchEvents = async () => {
    try {
      setLoading(true);
      setError("");
      const endpoint =
        activeIndex === 0 ? "/event/registered" : "/event/hosted";
      const res = await API.get(endpoint);

      // add role info
      const role = activeIndex === 0 ? "Going" : "Hosting";
      const enriched = res.data.map((e: any) => ({ ...e, role }));

      setEvents(enriched);
    } catch (err: any) {
      setError("Failed to load events");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, [activeIndex]);

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
        <SegmentedControl
          appearance="dark"
          activeFontStyle={{
            fontSize: 14,
            fontFamily: "Inter_medium",
            fontWeight: "bold",
            color: colors.text_primary,
          }}
          style={{ height: 36, marginBottom: 16 }}
          fontStyle={{
            color: colors.white,
          }}
          tintColor={colors.white}
          backgroundColor={colors.primary}
          values={["Going", "Hosting"]}
          selectedIndex={activeIndex}
          onChange={(event) => {
            setActiveIndex(event.nativeEvent.selectedSegmentIndex);
          }}
        />

        {loading && <ActivityIndicator />}
        {error !== "" && (
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
