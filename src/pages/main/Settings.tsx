import { getMyProfileQuery } from "@/services/user";
import { Card, CardContent, CardDescription, CardHeader, CardTitle, } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { AlertCircle, PencilIcon, PlusCircle } from "lucide-react";
import { Button } from '@/components/ui/button';

const Settings = () => {
  const { data, isLoading, isError } = getMyProfileQuery();
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
      </Card>

    </div>
  );
};

export default Settings;