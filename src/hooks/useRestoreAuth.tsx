import { logout, setAuthFromStorage } from "@/redux/slices/authSlice";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";

//To restore auth on app start
export const useRestoreAuth = () => {
  const dispatch = useDispatch();
  const [restored, setRestored] = useState(false);

  useEffect(() => {
    const restoreAuth = async () => {
      try {
        const token = await AsyncStorage.getItem("token");
        const user = await AsyncStorage.getItem("user");

        if (token && user) {
          dispatch(setAuthFromStorage({ token, user: JSON.parse(user) }));
        } else {
          dispatch(logout());
        }
      } finally {
        setRestored(true);
      }
    };
    restoreAuth();
  }, []);

  return restored;
};
