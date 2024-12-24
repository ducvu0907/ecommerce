import React, { useState } from 'react';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger, DialogDescription, } from '@/components/ui/dialog';
import { Label } from '@/components/ui/label';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import { Button } from '@/components/ui/button';
import { cities, countries, streets } from '@/helpers';

interface CreateAddressForm {
  token: string;
  country: string;
  street: string;
  city: string;
};

const AddressModal = () => {
  const [open, setOpen] = useState(false);
  const [address, setAddress] = useState<CreateAddressForm>({
    token: null,
    country: '',
    street: '',
    city: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log('Form submitted:', address);
    setOpen(false);
  };

  const availableStreets = address.country ? streets[address.country as keyof typeof streets] : [];
  const availableCities = address.country ? cities[address.country as keyof typeof cities] : [];

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button>Add New Address</Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-lg">
        <DialogHeader>
          <DialogTitle>Add New Address</DialogTitle>
          <DialogDescription>
            Select your address details from the available options
          </DialogDescription>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="space-y-2">
            <Label htmlFor="country">Country</Label>
            <Select
              value={address.country}
              onValueChange={(value) => setAddress({ 
                ...address, 
                country: value,
                street: '',
                city: '' 
              })}
            >
              <SelectTrigger>
                <SelectValue placeholder="Select a country" />
              </SelectTrigger>
              <SelectContent>
                {countries.map((country) => (
                  <SelectItem key={country.value} value={country.value}>
                    {country.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <Label htmlFor="street">Street Address</Label>
            <Select
              value={address.street}
              onValueChange={(value) => setAddress({ ...address, street: value })}
              disabled={!address.country}
            >
              <SelectTrigger>
                <SelectValue placeholder="Select a street" />
              </SelectTrigger>
              <SelectContent>
                {availableStreets.map((street) => (
                  <SelectItem key={street.value} value={street.value}>
                    {street.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <Label htmlFor="city">City</Label>
            <Select
              value={address.city}
              onValueChange={(value) => setAddress({ ...address, city: value })}
              disabled={!address.country}
            >
              <SelectTrigger>
                <SelectValue placeholder="Select a city" />
              </SelectTrigger>
              <SelectContent>
                {availableCities.map((city) => (
                  <SelectItem key={city.value} value={city.value}>
                    {city.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="flex justify-end gap-4">
            <Button variant="outline" type="button" onClick={() => setOpen(false)}>
              Cancel
            </Button>
            <Button type="submit">Save Address</Button>
          </div>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default AddressModal;