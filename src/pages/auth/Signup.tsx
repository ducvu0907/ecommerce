import React, { FormEvent, useState } from 'react';
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from '@/components/ui/card';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { User, Lock, Phone, MapPin } from 'lucide-react';
import useAuth from '@/hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import { validateSignupForm } from '@/helpers';
import { Role, SignupRequest, AddressRegisterRequest } from '@/types/models';
import { countries, streets, cities } from '@/helpers';

const Signup: React.FC = () => {
  const navigate = useNavigate();
  const { signupMutation } = useAuth();
  const { mutate: signup, isPending: isLoading } = signupMutation;

  const [formData, setFormData] = useState<SignupRequest>({
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    phone: '',
    role: Role.BUYER,
    address: {
      country: '',
      city: '',
      street: '',
    },
  });

  const [errors, setErrors] = useState<{ [key: string]: string }>({});

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    setErrors((prevErrors) => ({
      ...prevErrors,
      [name]: '',
    }));
  };

  const handleRoleChange = (value: Role) => {
    setFormData((prev) => ({
      ...prev,
      role: value,
    }));
  };

  const handleAddressChange = (field: keyof AddressRegisterRequest, value: string) => {
    setFormData((prev) => ({
      ...prev,
      address: {
        ...prev.address,
        [field]: value,
        ...(field === 'country' && { city: '', street: '' }),
        ...(field === 'city' && { street: '' }),
      },
    }));

    setErrors((prev) => ({
      ...prev,
      [`address.${field}`]: '',
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const { isValid, errors } = validateSignupForm(formData);
    if (isValid) {
      signup(formData);
    } else {
      setErrors(errors);
    }
  };

  const availableCities = formData.address.country ?  cities[formData.address.country as keyof typeof cities] : [];
  const availableStreets = formData.address.city ?  streets[formData.address.city as keyof typeof streets] : [];

  return (
    <div className="min-h-screen w-full flex items-center justify-center bg-slate-50">
      <Card className="w-full max-w-lg mx-4">
        <CardHeader className="space-y-1">
          <CardTitle className="text-2xl font-bold text-center">Create an account</CardTitle>
          <CardDescription className="text-center">
            Enter your information to create your account
          </CardDescription>
        </CardHeader>
        <CardContent>

          <form onSubmit={handleSubmit} className="space-y-4">

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label htmlFor="firstName">First Name</Label>
                <div className="relative">
                  <Input
                    id="firstName"
                    name="firstName"
                    placeholder="John"
                    value={formData.firstName}
                    onChange={handleInputChange}
                    className={errors.firstName ? 'border-red-500' : ''}
                  />
                </div>
                {errors.firstName && (
                  <p className="text-sm text-red-500">{errors.firstName}</p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="lastName">Last Name</Label>
                <div className="relative">
                  <Input
                    id="lastName"
                    name="lastName"
                    placeholder="Doe"
                    value={formData.lastName}
                    onChange={handleInputChange}
                    className={errors.lastName ? 'border-red-500' : ''}
                  />
                </div>
                {errors.lastName && (
                  <p className="text-sm text-red-500">{errors.lastName}</p>
                )}
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="username">Username</Label>
              <div className="relative">
                <User className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
                <Input
                  id="username"
                  name="username"
                  placeholder="johndoe"
                  value={formData.username}
                  onChange={handleInputChange}
                  className={`pl-10 ${errors.username ? 'border-red-500' : ''}`}
                />
              </div>
              {errors.username && (
                <p className="text-sm text-red-500">{errors.username}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="password">Password</Label>
              <div className="relative">
                <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
                <Input
                  id="password"
                  name="password"
                  type="password"
                  placeholder="••••••••"
                  value={formData.password}
                  onChange={handleInputChange}
                  className={`pl-10 ${errors.password ? 'border-red-500' : ''}`}
                />
              </div>
              {errors.password && (
                <p className="text-sm text-red-500">{errors.password}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="phone">Phone Number</Label>
              <div className="relative">
                <Phone className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
                <Input
                  id="phone"
                  name="phone"
                  placeholder="0912345678"
                  value={formData.phone}
                  onChange={handleInputChange}
                  className={`pl-10 ${errors.phone ? 'border-red-500' : ''}`}
                />
              </div>
              {errors.phone && (
                <p className="text-sm text-red-500">{errors.phone}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label>Account Type</Label>
              <Select onValueChange={handleRoleChange} value={formData.role}>
                <SelectTrigger>
                  <SelectValue placeholder="Select your account type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value={Role.BUYER}>Buyer</SelectItem>
                  <SelectItem value={Role.SELLER}>Seller</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {formData.role === Role.SELLER && (
              <div className="space-y-4">
                <div className="relative space-y-2">
                  <Label>Country</Label>
                  <div className="relative">
                    <MapPin className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
                    <Select
                      value={formData.address.country}
                      onValueChange={(value) => handleAddressChange('country', value)}
                    >
                      <SelectTrigger className="pl-10">
                        <SelectValue placeholder="Select your country" />
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
                  {errors['address.country'] && (
                    <p className="text-sm text-red-500">{errors['address.country']}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label>City</Label>
                  <Select
                    value={formData.address.city}
                    onValueChange={(value) => handleAddressChange('city', value)}
                    disabled={!formData.address.country}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="Select your city" />
                    </SelectTrigger>
                    <SelectContent>
                      {availableCities.map((city) => (
                        <SelectItem key={city.value} value={city.value}>
                          {city.label}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                  {errors['address.city'] && (
                    <p className="text-sm text-red-500">{errors['address.city']}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label>Street Address</Label>
                  <Select
                    value={formData.address.street}
                    onValueChange={(value) => handleAddressChange('street', value)}
                    disabled={!formData.address.city}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="Select your street" />
                    </SelectTrigger>
                    <SelectContent>
                      {availableStreets.map((street) => (
                        <SelectItem key={street.value} value={street.value}>
                          {street.label}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                  {errors['address.street'] && (
                    <p className="text-sm text-red-500">{errors['address.street']}</p>
                  )}
                </div>
              </div>
            )}

            <Button
              type="submit"
              className="w-full"
              disabled={isLoading}
            >
              {isLoading ? 'Creating account...' : 'Create account'}
            </Button>
          </form>
        </CardContent>
        <CardFooter className="flex justify-center">
          <div className="text-center text-sm">
            <Button variant="link" className="px-0" onClick={() => navigate('/login')}>
              Already have an account? Sign in
            </Button>
          </div>
        </CardFooter>
      </Card>
    </div>
  );
};

export default Signup;