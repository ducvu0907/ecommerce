import React, { useContext } from 'react';
import { MapPin, Trash2, Loader } from 'lucide-react';
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from '@/components/ui/alert-dialog';
import { Button } from '@/components/ui/button';
import { AuthContext } from '@/contexts/AuthContext';
import { deleteAddress } from '@/services/user';
import { AddressData } from '@/types/models';

interface AddressItemProps {
  address: AddressData;
}

const AddressItem: React.FC<AddressItemProps> = ({ address }) => {
  const { token } = useContext(AuthContext);
  const { mutate, isPending } = deleteAddress();

  const handleDeleteAddress = async () => {
    mutate({ addressId: address.id, request: { token: token || "" } });
  };

  return (
    <div className="group flex items-start space-x-6 p-6 border border-gray-200 rounded-xl bg-white shadow-sm hover:shadow-md transition-shadow duration-200">
      <div className="flex-shrink-0">
        <div className="p-2 bg-blue-50 rounded-lg">
          <MapPin className="h-6 w-6 text-blue-500" />
        </div>
      </div>
      <div className="flex-grow space-y-1">
        <p className="text-lg font-semibold text-gray-900">{address.country}</p>
        <p className="text-gray-700">{address.street}</p>
        <p className="text-gray-600">{address.city}</p>
      </div>
      <div className="flex-shrink-0">
        <AlertDialog>
          <AlertDialogTrigger asChild>
            <Button
              variant="ghost"
              size="icon"
              className="opacity-0 group-hover:opacity-100 transition-opacity duration-200 h-9 w-9 text-gray-500 hover:text-red-600 hover:bg-red-50"
              disabled={isPending}
            >
              {isPending ? 
                <Loader className="h-5 w-5 animate-spin" /> : 
                <Trash2 className="h-5 w-5 text-red-500" />
              }
            </Button>
          </AlertDialogTrigger>
          <AlertDialogContent className="sm:max-w-[425px]">
            <AlertDialogHeader>
              <AlertDialogTitle className="text-red-600">Delete Address</AlertDialogTitle>
              <AlertDialogDescription className="text-gray-600">
                Are you sure you want to delete this address? This action cannot be undone.
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel className="text-gray-500">Cancel</AlertDialogCancel>
              <AlertDialogAction
                onClick={handleDeleteAddress}
                className="bg-red-500 hover:bg-red-600 text-white focus:ring-red-500"
              >
                Delete
              </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      </div>
    </div>
  );
};

export default AddressItem;