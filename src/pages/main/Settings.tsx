import { AuthContext } from "@/contexts/AuthContext";
import { getMyProfile } from "@/services/user";
import { useContext, useState } from "react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle, } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { AlertCircle, MapPin, PencilIcon, PlusCircle } from "lucide-react";
import AddressInputModal from "@/components/address/AddressInputModal";
import { Button } from '@/components/ui/button';
import AddressItem from "@/components/address/AddressItem";
import UpdateProfileModal from "@/components/user/UpdateProfileModal";

const Settings = () => {
  const [showAddressModal, setShowAddressModal] = useState<boolean>(false);
  const [showUpdateModal, setShowUpdateModal] = useState<boolean>(false);
  const { token } = useContext(AuthContext);
  const { data, isLoading, isError } = getMyProfile(token || "");
  const user = data?.result;

  if (isLoading) {
    return (
      <div className="space-y-4 p-4">
        <Skeleton className="h-8 w-[200px]" />
        <Skeleton className="h-[200px] w-full" />
      </div>
    );
  }

  if (isError || !user) {
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

      {showAddressModal && <AddressInputModal isOpen={showAddressModal} onClose={() => setShowAddressModal(false)} />}

      {showUpdateModal && <UpdateProfileModal userData={{...user}} isOpen={showUpdateModal} onClose={() => setShowUpdateModal(false)} />}

      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Personal Information</CardTitle>
            <CardDescription>Your profile details and account information</CardDescription>
          </div>
          <Button 
            variant={"default"}
            onClick={() => setShowUpdateModal(true)}
            className="flex items-center gap-2"
          >
            <PencilIcon className="h-4 w-4" />
            Edit Profile
          </Button>
        </CardHeader>

        <CardContent>
          <div className="grid grid-cols-3 gap-4">
            <div>
              <p className="text-sm font-medium text-gray-500">Username</p>
              <p className="text-lg">{user.username}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">First Name</p>
              <p className="text-lg">{user.firstName}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Last Name</p>
              <p className="text-lg">{user.lastName}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Role</p>
              <p className="text-lg capitalize">{user.role.toLowerCase()}</p>
            </div>
            <div>
              <p className="text-sm font-medium text-gray-500">Phone</p>
              <p className="text-lg">{user.phone}</p>
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
            variant={"outline"}
            onClick={() => setShowAddressModal(true)}
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