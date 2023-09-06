import { StatusBar } from "expo-status-bar";
import { useEffect } from "react";
import { Text, View, TouchableOpacity } from "react-native";
import WorkoutCard from "./src/components/WorkoutCard";

export default function App() {
    return (
        <>
            <View className="h-20 w-full bg-white"></View>
            <View className="bg-white">
                <View className="flex w-full flex-row justify-between h-10  px-4">
                    <Text className="text-3xl text-gray-800 font-bold">A</Text>
                    <TouchableOpacity>
                        <Text>Adicionar</Text>
                    </TouchableOpacity>
                </View>

                <StatusBar style="auto" translucent={true} />
            </View>
        </>
    );
}
