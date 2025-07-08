import RootNavigation from "@/navigation/RootNavigation";
import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import "../../global.css";

const App = () => {
  return (
    <NavigationContainer>
      <RootNavigation />
    </NavigationContainer>
  );
};

export default App;
