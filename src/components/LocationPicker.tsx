import { X } from "lucide-react-native";
import React, { useEffect, useState } from "react";
import {
  ActivityIndicator,
  FlatList,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  View,
} from "react-native";
import Typo from "./Typo";

const GEOAPIFY_API_KEY = "3e7767d18bd547e9bc4e414bdbcfa721";

export interface AddressSuggestion {
  properties: {
    formatted: string;
    address_line1: string;
    address_line2: string;
    city: string;
    state: string;
    postcode: string;
    country: string;
    lat: number;
    lon: number;
  };
  geometry: {
    coordinates: [number, number];
  };
}

interface LocationPickerProps {
  selected: string | null;
  setSelected: (value: string | null) => void;
  placeholder?: string;
}

const LocationPicker: React.FC<LocationPickerProps> = ({
  selected,
  setSelected,
  placeholder = "Start typing provinces, cities",
}) => {
  const [query, setQuery] = useState("");
  const [suggestions, setSuggestions] = useState<AddressSuggestion[]>([]);
  const [loading, setLoading] = useState(false);
  const [isFocused, setIsFocused] = useState(false);

  useEffect(() => {
    const fetchSuggestions = async () => {
      if (query.length < 2) {
        setSuggestions([]);
        return;
      }

      setLoading(true);
      try {
        const url = `https://api.geoapify.com/v1/geocode/autocomplete?text=${encodeURIComponent(
          query
        )}&filter=countrycode:in&limit=5&apiKey=${GEOAPIFY_API_KEY}`;

        const response = await fetch(url);
        const data = await response.json();

        if (data.features) {
          const filtered = data.features
            .map((item: any) => {
              const city = item.properties.city || item.properties.state;
              const country = item.properties.country;
              if (city && country) {
                return {
                  ...item,
                  properties: {
                    ...item.properties,
                    formatted: `${city}, ${country}`, // Only city + country
                  },
                };
              }
              return null;
            })
            .filter(Boolean); // Remove nulls

          setSuggestions(filtered);
        } else {
          setSuggestions([]);
        }
      } catch (error) {
        console.error("Geoapify fetch error:", error);
        setSuggestions([]);
      } finally {
        setLoading(false);
      }
    };

    fetchSuggestions();
  }, [query]);

  useEffect(() => {
    if (selected && selected !== query) {
      setQuery(selected);
    }
  }, [selected]);

  const handleSelect = (item: AddressSuggestion) => {
    setSelected(item.properties.formatted);
    setQuery(item.properties.formatted); // update input field
    setSuggestions([]);
  };

  const clearSelection = () => {
    setSelected(null);
    setQuery("");
    setSuggestions([]);
  };

  return (
    <View style={styles.container}>
      {/* Input field */}
      <View
        style={[
          styles.inputWrapper,
          {
            borderColor: isFocused ? "#7690E4" : "#EDF1FF",
            borderWidth: 2,
          },
        ]}
      >
        <TextInput
          value={query}
          onChangeText={(text) => {
            setQuery(text);
            setSelected(null);
          }}
          placeholder={placeholder}
          placeholderTextColor="#8A95B4"
          style={styles.input}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
        />
        {query.length > 0 && (
          <TouchableOpacity onPress={clearSelection}>
            <X size={20} strokeWidth={2.5} color="#3A4155" />
          </TouchableOpacity>
        )}
      </View>

      {/* Loading or Suggestions */}
      {query.length > 0 && !selected && (
        <View style={styles.dropdown}>
          {loading ? (
            <View style={styles.loadingContainer}>
              <ActivityIndicator size="small" color="#7690E4" />
            </View>
          ) : (
            <FlatList
              data={suggestions}
              keyExtractor={(item, index) => item.properties.formatted + index}
              keyboardShouldPersistTaps="handled"
              renderItem={({ item }) => (
                <TouchableOpacity
                  style={styles.optionItem}
                  onPress={() => handleSelect(item)}
                >
                  <Typo font="Inter_semiBold" size={14}>
                    {item.properties.formatted}
                  </Typo>
                </TouchableOpacity>
              )}
            />
          )}
        </View>
      )}
    </View>
  );
};

export default LocationPicker;

const styles = StyleSheet.create({
  container: {
    marginTop: 0,
  },
  inputWrapper: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    borderRadius: 12,
    paddingHorizontal: 12,
    width: "100%",
    height: 55,
    backgroundColor: "#F8FAFF",
  },
  input: {
    flex: 1,
    fontSize: 16,
    fontFamily: "Inter_regular",
    color: "#3A4155",
    paddingTop: 0,
    paddingBottom: 0,
    textAlignVertical: "center",
  },
  dropdown: {
    marginTop: 6,
    borderColor: "#EDF1FF",
    borderWidth: 1,
    borderRadius: 12,
    maxHeight: 190,
    backgroundColor: "#fff",
  },
  optionItem: {
    padding: 16,
    borderBottomColor: "#EDF1FF",
    borderBottomWidth: 1,
  },
  loadingContainer: {
    padding: 16,
    alignItems: "center",
    justifyContent: "center",
  },
});
