import { AuthContext } from "@/contexts/AuthContext";
import { getMyProfile } from "@/services/user";
import React, { useContext } from "react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle, } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { AlertCircle, MapPin, PencilIcon, PlusCircle, Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { AddressData } from "@/types/models";

interface AddressItemProps {
  address: AddressData;
  onDelete: (addressId: string) => void;
};

const AddressItem: React.FC<AddressItemProps> = ({ address, onDelete }) => {
  return (
    <div className="flex items-start space-x-4 p-4 border rounded-lg bg-white">
      <MapPin className="h-5 w-5 text-gray-500 mt-1" />
      <div className="flex-grow">
        <p className="font-medium">{address.country}</p>
        <p className="text-sm text-gray-600">{address.street}</p>
        <p className="text-sm text-gray-600">{address.city}</p>
      </div>
      <div className="flex space-x-2">
        <Button
          variant="outline"
          size="icon"
          onClick={() => onDelete(address.id)}
          className="h-8 w-8 text-red-500 hover:text-red-600"
        >
          <Trash2 className="h-4 w-4" />
        </Button>
      </div>
    </div>
  );
};

const Settings = () => {
  const { token } = useContext(AuthContext);
  const { data, isLoading, isError } = getMyProfile(token || "");
  const user = data?.result;

  // placeholder
  const handleDeleteAddress = (addressId) => {
    console.log("Delete address:", addressId);
  };

  // placeholder
  const handleAddAddress = () => {
    console.log("Add new address");
  };

  // placeholder
  const handleEditProfile = () => {
    console.log("Edit profile");
  };

  if (isLoading) {
    return (
      <div className="space-y-4 p-4">
        <Skeleton className="h-8 w-[200px]" />
        <Skeleton className="h-[200px] w-full" />
      </div>
    );
  }

  if (isError) {
    return (
      <Alert variant="destructive" className="m-4">
        <AlertCircle className="h-4 w-4" />
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>
          Failed to load user profile. Please try again later.
        </AlertDescription>
      </Alert>
    );
  }

  return (
    <div className="container mx-auto p-4 space-y-6">

      <Card>

        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Personal Information</CardTitle>
            <CardDescription>Your profile details and account information</CardDescription>
          </div>
          <Button 
            variant="outline" 
            onClick={handleEditProfile}
            className="flex items-center gap-2"
          >
            <PencilIcon className="h-4 w-4" />
            Edit Profile
          </Button>
        </CardHeader>

        <CardContent>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="text-sm font-medium text-gray-500">Username</p>
              <p className="text-lg">{user?.username}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Role</p>
              <p className="text-lg capitalize">{user?.role.toLowerCase()}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Full Name</p>
              <p className="text-lg">{`${user?.firstName} ${user?.lastName}`}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Phone</p>
              <p className="text-lg">{user?.phone}</p>
            </div>
          </div>
        </CardContent>

      </Card>

      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Addresses</CardTitle>
            <CardDescription>Manage your saved addresses</CardDescription>
          </div>
          <Button 
            onClick={handleAddAddress}
            className="flex items-center gap-2"
          >
            <PlusCircle className="h-4 w-4" />
            New Address
          </Button>
        </CardHeader>
        <CardContent className="space-y-4">
          {user?.addresses.map((address) => (
            <AddressItem
              key={address.id}
              address={address}
              onDelete={handleDeleteAddress}
            />
          ))}
          {user?.addresses.length === 0 && (
            <div className="text-center text-gray-500 py-8">
              <MapPin className="h-8 w-8 mx-auto mb-2 text-gray-400" />
              <p>No addresses found</p>
              <p className="text-sm">Add an address to get started</p>
            </div>
          )}
        </CardContent>
      </Card>

    </div>
  );
};

export default Settings;