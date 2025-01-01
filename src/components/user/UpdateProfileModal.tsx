import React, { useContext, useState } from "react";
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { updateMyProfile } from "@/services/user";
import { AuthContext } from "@/contexts/AuthContext";
import { UserUpdateRequest } from "@/types/models";

interface UpdateProfileModalProps {
  isOpen: boolean;
  onClose: () => void;
  userData: Omit<UserUpdateRequest, "token">;
}

const UpdateProfileModal: React.FC<UpdateProfileModalProps> = ({ isOpen, onClose, userData }) => {
  const { token } = useContext(AuthContext);
  const { mutate, isPending } = updateMyProfile();
  const [formValues, setFormValues] = useState<Omit<UserUpdateRequest, "token">>(userData);

  const handleInputChange = (field: keyof typeof formValues) => (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormValues((prev) => ({ ...prev, [field]: e.target.value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutate({token: token || "", ...formValues});
    onClose();
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Update Profile</DialogTitle>
        </DialogHeader>
        <form>
          <div className="space-y-4">
            <div>
              <Label htmlFor="username">Username</Label>
              <Input
                id="username"
                value={formValues.username}
                onChange={handleInputChange("username")}
                placeholder="Enter username"
              />
            </div>
            <div>
              <Label htmlFor="firstName">First Name</Label>
              <Input
                id="firstName"
                value={formValues.firstName}
                onChange={handleInputChange("firstName")}
                placeholder="Enter first name"
              />
            </div>
            <div>
              <Label htmlFor="lastName">Last Name</Label>
              <Input
                id="lastName"
                value={formValues.lastName}
                onChange={handleInputChange("lastName")}
                placeholder="Enter last name"
              />
            </div>
            <div>
              <Label htmlFor="phone">Phone</Label>
              <Input
                id="phone"
                value={formValues.phone}
                onChange={handleInputChange("phone")}
                placeholder="Enter phone number"
              />
            </div>
          </div>
        </form>
        <DialogFooter>
          <Button variant="outline" onClick={onClose}>
            Cancel
          </Button>
          <Button onClick={handleSubmit} disabled={isPending}>Update</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default UpdateProfileModal;
