import React from "react";
import { TouchableOpacity, View } from "react-native";
import Typo from "./Typo";
import { colors } from "@/constants/colors";

type Tag = {
  label: string;
  value: string;
};

type Props = {
  options: Tag[];
  selectedValues: string[];
  onChange: (selected: string[]) => void;
};

const MultiSelectTags: React.FC<Props> = ({
  options,
  selectedValues,
  onChange,
}) => {
  const toggleValue = (value: string) => {
    if (selectedValues.includes(value)) {
      onChange(selectedValues.filter((v) => v !== value));
    } else {
      onChange([...selectedValues, value]);
    }
  };

  return (
    <View
      style={{
        flexDirection: "row",
        flexWrap: "wrap",
        gap: 8,
        marginVertical: 8,
      }}
    >
      {options.map((option) => {
        const isSelected = selectedValues.includes(option.value);
        return (
          <TouchableOpacity
            key={option.value}
            onPress={() => toggleValue(option.value)}
            style={{
              padding: 12,
              borderRadius: 12,
              borderWidth: 2,
              borderColor: isSelected ? colors.primary : "#F1F4FC",
              backgroundColor: isSelected ? "#EDF1FF" : "#FFFFFF",
            }}
          >
            <Typo
              font="Inter_bold"
              size={14}
              style={{
                color: isSelected ? colors.primary : "#8A95B4",
              }}
            >
              {option.label}
            </Typo>
          </TouchableOpacity>
        );
      })}
    </View>
  );
};

export default MultiSelectTags;
