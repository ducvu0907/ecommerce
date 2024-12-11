import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "../ui/button";
import { CheckCircle, XCircle, Loader2, AlertTriangle } from "lucide-react";
import { getDiscounts } from "@/services/discount";
import { useState } from "react";
import { DiscountData } from "@/types/models";

const DiscountList = () => {
  const {data, isLoading, isError} = getDiscounts();
  const discounts = data?.result;
  const [selectedDiscount, setSelectedDiscount] = useState<DiscountData | null>(null);

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
                <TableRow
                  key={discount.id}
                  className={
                    selectedDiscount?.id === discount.id
                      ? "bg-blue-50"
                      : ""
                  }
                >
                  <TableCell>{discount.description}</TableCell>
                  <TableCell>
                    {discount.percent !== null
                      ? `${discount.percent}%`
                      : discount.amount !== null
                        ? `$${discount.amount.toFixed(2)}`
                        : 'N/A'}
                  </TableCell>
                  <TableCell>{new Date(discount.startDate).toLocaleDateString()}</TableCell>
                  <TableCell>{new Date(discount.endDate).toLocaleDateString()}</TableCell>
                  <TableCell>
                    {discount.isActive ? (
                      <CheckCircle className="h-5 w-5 text-green-500" />
                    ) : (
                      <XCircle className="h-5 w-5 text-red-500" />
                    )}
                  </TableCell>
                  <TableCell>
                    <Button
                      variant={selectedDiscount?.id === discount.id ? "default" : "outline"}
                      size="sm"
                      onClick={() => handleSelectDiscount(discount)}
                      disabled={!discount.isActive}
                    >
                      {selectedDiscount?.id === discount.id ? "Selected" : "Select"}
                    </Button>
                  </TableCell>
                </TableRow>
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