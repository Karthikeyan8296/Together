import { CenterHeader } from "@/components/CenterHeader";
import ImageUpload from "@/components/ImageUploader";
import InputField from "@/components/InputField";
import ScreenWrapper from "@/components/ScreenWrapper";
import React, { useState } from "react";
import { View } from "react-native";

const CreateEvent = () => {
  const [coverImage, setCoverImage] = useState(String || null);

  return (
    <ScreenWrapper>
      <CenterHeader title="Create Event" />

      <View className="flex-1 items-center mt-6 mx-8">
        <ImageUpload
          file={coverImage}
          onSelect={(file) => setCoverImage(file)}
          //@ts-ignore
          onClear={() => setCoverImage(null)}
          placeholder={""}
          containerStyle={{ height: 250, width: 250 }}
          imageStyle={{ height: 250, width: 250 }}
          aspectRatio={[1, 1]}
          allowEditing={true}
        />

        <View className="w-full mt-6">
          <View className="mb-4">
            <InputField placeholder="Event Name" />
          </View>
        </View>

        {/* start date, and end date inputs can go here */}
        
        <InputField placeholder="Choose location" />
        <InputField placeholder="Add Description" />
      </View>
    </ScreenWrapper>
  );
};

export default CreateEvent;
