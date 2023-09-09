import { StatusBar } from "expo-status-bar";
import { useEffect } from "react";
import { Text, View, TouchableOpacity } from "react-native";
import WorkoutCard from "./src/components/WorkoutCard";
import * as AuthSession from "expo-auth-session";

export default function App() {
    return (
        <View className="flex-1 bg-gray-900">
            <View className="flex w-full flex-row justify-between h-10  px-4">
                <Text className="text-3xl text-white font-bold">A</Text>
                <TouchableOpacity>
                    <Text>Adicionar</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}
