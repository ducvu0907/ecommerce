import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Table, TableBody, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { getDiscounts } from "@/services/discount";
import { useState } from "react";
import { DiscountData } from "@/types/models";
import DiscountItem from "./DiscountItem";
import { AlertTriangle, Loader2 } from "lucide-react";

const DiscountList = () => {
  const {data, isLoading, isError} = getDiscounts();
  const discounts = data?.result;
  const [selectedDiscount, setSelectedDiscount] = useState<DiscountData | null>(null);

  // FIXME 
  const handleSelectDiscount = (discount: DiscountData) => {
    setSelectedDiscount(discount);
  };

  if (isLoading) {
    return (
      <Card>
        <CardHeader>
          <CardTitle>Discounts</CardTitle>
        </CardHeader>
        <CardContent className="flex justify-center items-center h-40">
          <Loader2 className="h-8 w-8 animate-spin text-blue-500" />
        </CardContent>
      </Card>
    );
  }

  if (isError) {
    return (
      <Card>
        <CardHeader>
          <CardTitle>Discounts</CardTitle>
        </CardHeader>
        <CardContent className="flex justify-center items-center h-40 text-destructive">
          <AlertTriangle className="h-8 w-8 mr-2" />
          <span>Error loading discounts</span>
        </CardContent>
      </Card>
    );
  }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Available Discounts</CardTitle>
      </CardHeader>
      {!discounts || discounts.length === 0 ?
        <CardContent className="text-center">
          No discount available
        </CardContent> :
        <CardContent>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Description</TableHead>
                <TableHead>Discount</TableHead>
                <TableHead>Start Date</TableHead>
                <TableHead>End Date</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Action</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {discounts?.map((discount) => (
                <DiscountItem discount={discount}/>
              ))}
            </TableBody>
          </Table>
          {selectedDiscount && (
            <div className="mt-4 text-sm text-muted-foreground">
              Selected Discount: {selectedDiscount.description}
            </div>
          )}
        </CardContent>
      }
    </Card>
  );
};

export default DiscountList;