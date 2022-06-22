
import { AddPhotoAlternateRounded } from '@mui/icons-material';
import { Box } from '@mui/material';
import ImageUploading from 'react-images-uploading';
import { addProfilePhoto } from '../api/controllers/seller-controller';

const SellerProfilePhoto = ({ photoUrl, fetchStore }) => {

    const onChange = async (imageList, addUpdateIndex) => {

        let formData = new FormData();

        formData.append('file', imageList[0].file)

        await addProfilePhoto(formData);

        fetchStore();
    };

    return (

        <ImageUploading

            onChange={onChange}
            dataURLKey="data_url"
        >
            {({
                onImageUpload,
            }) => (
                <Box onClick={onImageUpload} className='store-profile-img' sx={{ backgroundImage: `url(${photoUrl})`, cursor: 'pointer', overflow: "hidden" }}>
                    <Box sx={{ width: "100%", height: "100%", display: "flex", justifyContent: "center", alignItems: "center", backgroundColor: "rgba(255, 255, 255, .5)" }}>
                        <AddPhotoAlternateRounded sx={{ color: "primary" }} />
                    </Box>
                </Box>
            )
            }
        </ImageUploading >

    );
}

export default SellerProfilePhoto;