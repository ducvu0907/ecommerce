import { getUser } from "@/services/user";
import { CheckCircle2, ShieldCheck, MapPin, Badge, Phone, MessageCircle, Store, ShoppingBag, Users, MessageSquareText } from "lucide-react";
import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Button } from "@/components/ui/button";
import React from 'react';

interface SellerInfoProps {
  sellerId: string;
}

const SellerInfo: React.FC<SellerInfoProps> = ({ sellerId }) => {
  const { data, isLoading, isError } = getUser(sellerId);
  const seller = data?.result;

  if (isLoading) {
    return (
      <Card>
        <CardHeader>
          <div className="flex items-center space-x-4">
            <Skeleton className="h-16 w-16 rounded-full" />
            <div className="space-y-2 flex-1">
              <Skeleton className="h-4 w-[250px]" />
              <Skeleton className="h-4 w-[200px]" />
            </div>
          </div>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-3 gap-4">
            {[1, 2, 3].map((item) => (
              <div key={item} className="flex flex-col items-center space-y-2">
                <Skeleton className="h-6 w-6 rounded-full" />
                <Skeleton className="h-4 w-[100px]" />
              </div>
            ))}
          </div>
        </CardContent>
      </Card>
    );
  }

  if (isError) {
    return (
      <Alert variant="destructive">
        <ShieldCheck className="h-4 w-4" />
        <AlertTitle>Error Loading Seller Information</AlertTitle>
        <AlertDescription>
          We couldn't retrieve the seller details. Please try again later or check your connection.
        </AlertDescription>
      </Alert>
    );
  }

  return (
    <Card className="flex mb-4">
      <CardContent className="p-6">
        <div className="flex items-center space-x-6 mb-4">
          <img
            src={`https://avatar.iran.liara.run/username?username=${seller.firstName}+${seller.lastName}`}
            alt={`${seller?.firstName} ${seller?.lastName}`}
            className="w-16 h-16 rounded-full object-cover"
          />

          <div className="flex-1">
            <div className="flex items-center space-x-2">
              <h3 className="text-lg font-semibold">
                {seller?.firstName} {seller?.lastName}
              </h3>
              <CheckCircle2 className="w-5 h-5 text-green-500" />
            </div>

            <div className="mt-1 text-sm text-muted-foreground">
              <div className="flex items-center">
                <Phone className="w-4 h-4" />
                <p className="ml-2">{seller?.phone}</p>
              </div>
              {seller?.addresses && seller.addresses.length > 0 && (
                <div className="flex items-center">
                  <MapPin className="w-4 h-4" />
                  <p className="ml-2">
                    {seller.addresses[0].street}, {seller.addresses[0].city}, {seller.addresses[0].country}
                  </p>
                </div>
              )}
            </div>
          </div>
        </div>

        <div className="flex space-x-4">
          <Button
            variant="outline"
            className="flex-1"
          >
            <MessageCircle className="mr-2 h-4 w-4" />
            Message
          </Button>
          <Button
            variant="default"
            className="flex-1"
          >
            <Store className="mr-2 h-4 w-4" />
            View Shop
          </Button>
        </div>
      </CardContent>

      <CardContent className="border-l-2 w-full">
        <div className="flex h-full items-center justify-center italic font-semibold">statistics go here</div>
      </CardContent>

    </Card>
  );
};

export default SellerInfo;