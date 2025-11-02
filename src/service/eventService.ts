import API from "@/config/api";

export type Host = {
  _id: string;
  name: string;
  profileImage: string | null;
};

export type Participant = {
  userId: string;
  qrCode: string;
  checkedIn: boolean;
  _id: string;
  registeredAt: string;
};

export type EventLocation = {
  address: string;
};

export type Event = {
  _id: string;
  hostId: string | Host; // it can be an ID (for hosted) or object (for registered)
  eventName: string;
  description: string;
  category: string;
  coverImage: string;
  location: EventLocation;
  startTime: string;
  endTime: string;
  capacity: number;
  status: "upcoming" | "ongoing" | "completed";
  participants: Participant[];
  createdAt: string;
  __v?: number;
};

// Your Events (Hosting + Going)
export type YourEventsResponse = {
  hostedEvents: Event[];
  registeredEvents: Event[];
};
export async function getYourEvents(): Promise<YourEventsResponse> {
  const res = await API.get<YourEventsResponse>("/event/your-events");
  return res.data;
}

// Going
export async function getRegisteredEvents(): Promise<Event[]> {
  const res = await API.get<Event[]>("/event/registered");
  return res.data;
}

// Hosting
export async function getHostedEvents(): Promise<Event[]> {
  const res = await API.get<Event[]>("/event/hosted");
  return res.data;
}

//Create Event
export async function createEvent(eventData: Partial<Event>): Promise<Event> {
  const res = await API.post<Event>("/event", eventData);
  return res.data;
}
