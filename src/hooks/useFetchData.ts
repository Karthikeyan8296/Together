import API from "@/config/api";
import { useEffect, useState } from "react";

export function useFetchData<T>(endpoint: string) {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let isMounted = true;

    const fetchData = async () => {
      try {
        setLoading(true);
        const res = await API.get(endpoint); // uses interceptor for token
        if (isMounted) setData(res.data);
      } catch (err: any) {
        if (isMounted) setError(err.message || "Error fetching data");
      } finally {
        if (isMounted) setLoading(false);
      }
    };

    fetchData();

    return () => {
      isMounted = false;
    };
  }, [endpoint]);

  return { data, loading, error };
}
