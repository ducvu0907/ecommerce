import React, { FormEvent, useState } from 'react';
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from '@/components/ui/card';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { useSignup } from '@/hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import { validateSignupForm } from '@/helpers';

export interface SignupFormData {
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  phone: string;
  role: string; // seller, user
}

const Signup: React.FC = () => {
  const navigate = useNavigate();
  const { mutate: signup, isPending: isLoading } = useSignup();
  
  const [formData, setFormData] = useState<SignupFormData>({
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    phone: '',
    role: 'user'
  });

  const [errors, setErrors] = useState<Partial<SignupFormData>>({});

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    // Clear error when user starts typing
    if (errors[name as keyof SignupFormData]) {
      setErrors(prev => ({
        ...prev,
        [name]: undefined
      }));
    }
  };

  const handleRoleChange = (value: string) => {
    setFormData(prev => ({
      ...prev,
      role: value
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (validateSignupForm(formData)) {
      signup(formData);
    }
  };

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
                <Input
                  id="firstName"
                  name="firstName"
                  placeholder="John"
                  value={formData.firstName}
                  onChange={handleInputChange}
                  className={errors.firstName ? "border-red-500" : ""}
                />
                {errors.firstName && (
                  <p className="text-sm text-red-500">{errors.firstName}</p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="lastName">Last Name</Label>
                <Input
                  id="lastName"
                  name="lastName"
                  placeholder="Doe"
                  value={formData.lastName}
                  onChange={handleInputChange}
                  className={errors.lastName ? "border-red-500" : ""}
                />
                {errors.lastName && (
                  <p className="text-sm text-red-500">{errors.lastName}</p>
                )}
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="username">Username</Label>
              <Input
                id="username"
                name="username"
                placeholder="johndoe"
                value={formData.username}
                onChange={handleInputChange}
                className={errors.username ? "border-red-500" : ""}
              />
              {errors.username && (
                <p className="text-sm text-red-500">{errors.username}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="password">Password</Label>
              <Input
                id="password"
                name="password"
                type="password"
                placeholder="••••••••"
                value={formData.password}
                onChange={handleInputChange}
                className={errors.password ? "border-red-500" : ""}
              />
              {errors.password && (
                <p className="text-sm text-red-500">{errors.password}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="phone">Phone Number</Label>
              <Input
                id="phone"
                name="phone"
                placeholder="0912345678"
                value={formData.phone}
                onChange={handleInputChange}
                className={errors.phone ? "border-red-500" : ""}
              />
              {errors.phone && (
                <p className="text-sm text-red-500">{errors.phone}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label>Account Type</Label>
              <Select onValueChange={handleRoleChange} defaultValue={formData.role}>
                <SelectTrigger>
                  <SelectValue placeholder="Select your account type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="user">User</SelectItem>
                  <SelectItem value="seller">Seller</SelectItem>
                </SelectContent>
              </Select>
            </div>

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