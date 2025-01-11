import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Label } from "@/components/ui/label";
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { getCategoriesQuery } from "@/services/category";
import { createProductMutation } from "@/services/product";
import { CreateProductRequest } from "@/types/models";
import { CategoryData } from "@/types/models";
import { useNavigate } from "react-router-dom";

const CreateProduct = () => {
  const { mutate: createProduct, isPending } = createProductMutation();
  const { data, isLoading } = getCategoriesQuery();
  const categories = data?.result;
  const navigate = useNavigate();

  const form = useForm<CreateProductRequest>({
    defaultValues: {
      title: "",
      description: "",
      imageUrl: "",
      sku: "",
      price: 0,
      categoryId: "",
    },
  });

  const onSubmit = (data: CreateProductRequest) => {
    createProduct({ request: data });
    navigate("/my-products");
  };

  if (isLoading) {
    return <div>Loading categories...</div>;
  }

  return (
    <div className="max-w-md mx-auto p-6 bg-white shadow-md rounded-lg">
      <h1 className="text-2xl font-bold mb-6">Create Product</h1>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
          <FormField
            control={form.control}
            name="title"
            rules={{ required: "Title is required" }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="title">Title</Label>
                <FormControl>
                  <Input {...field} placeholder="Product Title" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="description"
            rules={{ required: "Description is required" }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="description">Description</Label>
                <FormControl>
                  <Input {...field} placeholder="Product Description" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="imageUrl"
            rules={{ required: "Image URL is required" }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="imageUrl">Image URL</Label>
                <FormControl>
                  <Input {...field} placeholder="Image URL" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="sku"
            rules={{ required: "SKU is required" }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="sku">SKU</Label>
                <FormControl>
                  <Input {...field} placeholder="Product SKU" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="price"
            rules={{ required: "Price is required", min: { value: 0, message: "Price must be a positive number" } }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="price">Price</Label>
                <FormControl>
                  <Input {...field} type="number" placeholder="Product Price" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="categoryId"
            rules={{ required: "Category is required" }}
            render={({ field }) => (
              <FormItem>
                <Label htmlFor="categoryId">Category</Label>
                <Select onValueChange={field.onChange} >
                  <FormControl>
                    <SelectTrigger>
                      <SelectValue placeholder="Select a category" />
                    </SelectTrigger>
                  </FormControl>
                  <SelectContent>
                    {categories?.map((category: CategoryData) => (
                      <SelectItem key={category.id} value={category.id}>
                        {category.title}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
                <FormMessage />
              </FormItem>
            )}
          />
          <Button type="submit" disabled={isPending} className="w-full">
            {isPending ? "Creating..." : "Create Product"}
          </Button>
        </form>
      </Form>
    </div>
  );
};

export default CreateProduct;